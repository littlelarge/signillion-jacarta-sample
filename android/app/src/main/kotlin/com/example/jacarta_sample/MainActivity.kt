package com.example.jacarta_sample

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.pkcs11.jacknji11.*
import ru.aladdin.mobileSDK.Default
import java.util.Random
import kotlin.coroutines.CoroutineContext

class MainActivity : FlutterFragmentActivity(), CoroutineScope {

    private val job = Job()
    private lateinit var usbManager: UsbManager
    private var usbReceiver: BroadcastReceiver? = null
    private var usbPermissionReceiver: BroadcastReceiver? = null

    companion object {
        private const val ACTION_USB_PERMISSION = "com.example.jacarta_sample.USB_PERMISSION"
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usbManager = getSystemService(Context.USB_SERVICE) as UsbManager
        setupUsbReceivers()
        checkConnectedDevices()
    }

    private fun setupUsbReceivers() {
        val usbFilter = IntentFilter().apply {
            addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        }
        usbReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    UsbManager.ACTION_USB_DEVICE_ATTACHED -> handleUsbDeviceAttached(intent)
                    UsbManager.ACTION_USB_DEVICE_DETACHED -> handleUsbDeviceDetached(intent)
                }
            }
        }
        registerReceiver(usbReceiver, usbFilter)

        usbPermissionReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == ACTION_USB_PERMISSION) {
                    val device: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        device?.let {
                            startTest()
                        }
                    } else {
                        showUsbDialog("Permission Denied", "USB permission not granted")
                    }
                }
            }
        }.also {
            registerReceiver(it, IntentFilter(ACTION_USB_PERMISSION))
        }
    }

    private fun handleUsbDeviceAttached(intent: Intent) {
        val device: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
        device?.let {
            if (usbManager.hasPermission(it)) {
                startTest()
            } else {
                requestUsbPermission(it)
            }

        }
    }

    private fun handleUsbDeviceDetached(intent: Intent) {
        val device: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
        device?.let {
            showUsbDialog("Device Detached", "JaCarta device was disconnected")
        }
    }

    private fun checkConnectedDevices() {
        usbManager.deviceList.values.forEach { device ->
            if (usbManager.hasPermission(device)) {
                startTest()
            } else {
                requestUsbPermission(device)
            }
        }
    }

    private fun requestUsbPermission(device: UsbDevice) {
        val permissionIntent = PendingIntent.getBroadcast(
            this,
            0,
            Intent(ACTION_USB_PERMISSION).apply {
                putExtra(UsbManager.EXTRA_DEVICE, device)
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        usbManager.requestPermission(device, permissionIntent)
    }

    private fun startTest() {
        launch(Dispatchers.IO) {
            try {
                performTest()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showUsbDialog("Error", e.message ?: "Unknown error")
                }
            }
        }
    }

    private suspend fun performTest() {
        var sessionHandle = 0L
        var slotId = 0L

        try {
            // Инициализация библиотеки
            var rv = C.Initialize()
            checkResult(rv, "C_Initialize")

            // Получение списка слотов
            val slotCount = LongRef(0)
            rv = C.GetSlotList(true, null, slotCount)
            checkResult(rv, "C_GetSlotList")

            if (slotCount.value == 0L) {
                withContext(Dispatchers.Main) {
                    showUsbDialog("Error", "No slots found")
                }
                return
            }

            val slots = LongArray(slotCount.value.toInt())
            rv = C.GetSlotList(true, slots, slotCount)
            checkResult(rv, "C_GetSlotList")

            // Поиск нужного токена
            val tokenInfo = CK_TOKEN_INFO()
            slotId = slots.firstOrNull { slot ->
                C.GetTokenInfo(slot, tokenInfo) == CKR.OK &&
                        tokenInfo.model.contains("JaCarta GOST 2.0")
            } ?: throw Exception("Applet \"JaCarta GOST 2.0\" not found")

            withContext(Dispatchers.Main) {
                showUsbDialog("Token Found", "JaCarta GOST 2.0 token detected")
            }

            // Открытие сессии
            val session = LongRef(0)
            rv = C.OpenSession(slotId, CKF.SERIAL_SESSION or CKF.RW_SESSION, null, null, session)
            checkResult(rv, "C_OpenSession")
            sessionHandle = session.value

            withContext(Dispatchers.Main) {
                showUsbDialog("Session Opened", "Session opened successfully")
            }

            // Аутентификация
            rv = C.Login(sessionHandle, CKU.USER, "123456")
            checkResult(rv, "C_Login")

            withContext(Dispatchers.Main) {
                showUsbDialog("Authenticated", "User logged in successfully")
            }

            // Генерация ключевой пары
            withContext(Dispatchers.Main) {
                showUsbDialog("Generating Key Pair", "")
            }

            val mech = CKM(CKM.GOSTR3410_256_KEY_PAIR_GEN, null)
            val pubKeyHandle = LongRef(0)
            val privKeyHandle = LongRef(0)

            rv = C.GenerateKeyPair(
                sessionHandle,
                mech,
                Default.pubKeyAttribs,
                Default.prKeyAttribs,
                pubKeyHandle,
                privKeyHandle
            )
            checkResult(rv, "C_GenerateKeyPair")

            // Подпись данных
            val data = ByteArray(128).apply { Random().nextBytes(this) }
            withContext(Dispatchers.Main) {
                showUsbDialog("Data to Sign", "Data: ${data.contentToString()}")
            }

            val signature = ByteArray(64)
            val signatureLength = LongRef(signature.size.toLong())

            val signMech = CKM(CKM.GOSTR3410_WITH_GOSTR3411_12_256, null)
            rv = C.SignInit(sessionHandle, signMech, privKeyHandle.value)
            checkResult(rv, "C_SignInit")

            rv = C.Sign(sessionHandle, data, signature, signatureLength)
            checkResult(rv, "C_Sign")

            withContext(Dispatchers.Main) {
                showUsbDialog("Data Signed", "Signature: ${signature.contentToString()}")
            }

            // Проверка подписи
            withContext(Dispatchers.Main) {
                showUsbDialog("Verifying Signature", "")
            }

            rv = C.VerifyInit(sessionHandle, signMech, pubKeyHandle.value)
            checkResult(rv, "C_VerifyInit")

            rv = C.Verify(sessionHandle, data, signature)
            checkResult(rv, "C_Verify")

            withContext(Dispatchers.Main) {
                showUsbDialog("Signature Valid", "The signature is valid")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                showUsbDialog("Error", e.message ?: "Unknown error occurred")
            }
        } finally {
            if (sessionHandle != 0L) {
                C.Logout(sessionHandle)
                C.CloseSession(sessionHandle)
            }
            C.Finalize()
        }
    }

    private fun checkResult(rv: Long, operation: String) {
        if (rv != CKR.OK) {
            val errorMsg = when (rv) {
                CKR.PIN_LOCKED -> "PIN locked. Use PinUserUnblock sample"
                CKR.USER_PIN_NOT_INITIALIZED -> "User PIN not initialized. Use init sample"
                else -> "Operation $operation failed with error code $rv"
            }
            throw Exception(errorMsg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        usbReceiver?.let { unregisterReceiver(it) }
        usbPermissionReceiver?.let { unregisterReceiver(it) }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)
    }

    private fun showUsbDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
package com.example.jecarta_sample

import android.app.Activity
import android.app.AlertDialog
import org.pkcs11.jacknji11.C
import org.pkcs11.jacknji11.CKF
import org.pkcs11.jacknji11.CKM
import org.pkcs11.jacknji11.CKR
import org.pkcs11.jacknji11.CKRException
import org.pkcs11.jacknji11.CKU
import org.pkcs11.jacknji11.CK_TOKEN_INFO
import org.pkcs11.jacknji11.LongRef

import java.io.UnsupportedEncodingException
import java.nio.ByteBuffer
import java.util.Random

import ru.aladdin.mobileSDK.Default
import ru.aladdin.mobileSDK.Default.DEFAULT_USER_PIN_GOST2

class Jecarta : Activity() {
    fun initUser() {
        var rv: Long = CKR.OK
        var slots: LongArray? = null
        val slotCount = LongRef()
        val tokenInfo = CK_TOKEN_INFO()
        val session = LongRef()

        // Метка устройства
        val label = "JaCarta"

        try {
            var gostFound = false
            var gostSlotId: Long = 0

            // Инициализация библиотеки
            rv = C.Initialize()
            if (rv != CKR.OK) {
                throw CKRException("C.Initialize", rv)
            }

            // Определение размера списка слотов с подключенными токенами
            rv = C.GetSlotList(true, null, slotCount)
            if (rv != CKR.OK) {
                throw CKRException("C.GetSlotList", rv)
            }

            // Слоты не найдены
            if (slotCount.value == 0L) {
                showUsbDialog("No slots found")
                return
            }

            slots = LongArray(slotCount.value.toInt())

            // Получение списка слотов с подключенными токенами
            rv = C.GetSlotList(true, slots, slotCount)
            if (rv != CKR.OK) {
                throw CKRException("C.GetSlotList", rv)
            }

            // Просмотр доступных слотов
            for (slotId in slots) {
                // Получение информации о токене в слоте
                rv = C.GetTokenInfo(slotId, tokenInfo)
                if (rv != CKR.OK) {
                    throw CKRException("C.GetTokenInfo", rv)
                }

                if (tokenInfo.model.contains("JaCarta GOST 2.0")) {
                    gostFound = true
                    gostSlotId = slotId
                    break
                }
            }

            if (!gostFound) {
                return
            }

            rv = C.InitToken(
                gostSlotId,
                DEFAULT_USER_PIN_GOST2,
                "JaCarta GOST 2.0"
            )
            if (rv != CKR.OK) {
                throw CKRException("C.EXTENSIONS.JC_KT2_InitToken", rv)
            }
        } catch (e: CKRException) {
        } catch (e: UnsupportedEncodingException) {
        } finally {
            if (session.value != 0L) {
                // Завершение сеанса пользователя/администратора
                C.Logout(session.value)

                // Закрытие сессии
                C.CloseSession(session.value)
            }

            // Завершение работы библиотеки
            C.Finalize()
        }

        return
    }

    fun signAndVerify() {
        var rv = CKR.OK
        var slots: LongArray? = null
        val slotCount = LongRef()
        val session = LongRef()

        val mech = CKM(CKM.GOSTR3410_256_KEY_PAIR_GEN, null)
        val relatedID = ByteBuffer.allocate(4).putInt(2012).array()

        // Переменная атрибута, определяющая место сохранения объекта
        val bToken = true

        // Дескрипторы объектов
        val pubKeyHandle = LongRef()
        val prKeyHandle = LongRef()

        // Буфер с данными для подписания
        val data = ByteArray(128)

        // Буфер с результирующей подписью
        val signature = ByteArray(64)

        // Длина буфера для подписи
        val signatureLength = LongRef(signature.size.toLong())

        try {
            var gostFound = false
            var gostSlotId = 0L

            // Инициализация библиотеки
            rv = C.Initialize()
            if (rv != CKR.OK) throw CKRException("C_Initialize", rv)

            showUsbDialog("Init library: ok\n")

            // Определение размера списка слотов с подключенными токенами
            rv = C.GetSlotList(true, null, slotCount)
            if (rv != CKR.OK) throw CKRException("C_GetSlotList", rv)

            if (slotCount.value == 0L) {
                showUsbDialog("JaCarta GOST 2.0 not found\nReconnect reader or insert card\n")
                return
            }

            slots = LongArray(slotCount.value.toInt())
            rv = C.GetSlotList(true, slots, slotCount)
            if (rv != CKR.OK) throw CKRException("C_GetSlotList", rv)

            val tokenInfo = CK_TOKEN_INFO()
            for (slotId in slots) {
                rv = C.GetTokenInfo(slotId, tokenInfo)
                if (rv != CKR.OK) throw CKRException("C.GetTokenInfo", rv)

                if (tokenInfo.model.contains("JaCarta GOST 2.0")) {
                    gostFound = true
                    gostSlotId = slotId
                    showUsbDialog("Applet \"JaCarta GOST 2.0\" found!\n")
                    break
                }
            }

            if (!gostFound) {
                showUsbDialog("Applet \"JaCarta GOST 2.0\" is not found\n")
                return
            }

            rv =
                C.OpenSession(gostSlotId, CKF.SERIAL_SESSION or CKF.RW_SESSION, null, null, session)
            if (rv != CKR.OK) throw CKRException("C.OpenSession", rv)

            showUsbDialog("Open session: ok\n")

            rv = C.Login(session.value, CKU.USER, DEFAULT_USER_PIN_GOST2)
            if (rv != CKR.OK) throw CKRException("C.Login", rv)

            showUsbDialog("Login: ok\n")

            rv = C.GenerateKeyPair(
                session.value,
                mech,
                Default.pubKeyAttribs,
                Default.prKeyAttribs,
                pubKeyHandle,
                prKeyHandle
            )
            if (rv != CKR.OK) throw CKRException("C.GenerateKeyPair", rv)

            showUsbDialog("Create keys: ok\n")

            Random().nextBytes(data)

            val operationMech = CKM(CKM.GOSTR3410_WITH_GOSTR3411_12_256, null)
            rv = C.SignInit(session.value, operationMech, prKeyHandle.value)
            if (rv != CKR.OK) throw CKRException("C.SignInit", rv)

            rv = C.Sign(session.value, data, signature, signatureLength)
            if (rv != CKR.OK) throw CKRException("C.Sign", rv)

            showUsbDialog("Sign data: ok\n")

            rv = C.VerifyInit(session.value, operationMech, pubKeyHandle.value)
            if (rv != CKR.OK) throw CKRException("C.VerifyInit", rv)

            rv = C.Verify(session.value, data, signature)
            if (rv != CKR.OK) throw CKRException("C.Verify", rv)

            showUsbDialog("Verify data: ok\n")
            showUsbDialog("DATA WAS SIGNED AND VERIFIED SUCCESSFULLY\n")

        } catch (e: CKRException) {
            showUsbDialog("${e.message}\n")
            when (e.ckr) {
                CKR.PIN_LOCKED -> showUsbDialog("For unlock user PIN retries, start \"PinUserUnblock\" sample\n")
                CKR.USER_PIN_NOT_INITIALIZED -> showUsbDialog("For initialize user PIN, start \"init\" sample\n")
                else -> showUsbDialog("Contact the developer\n")
            }
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } finally {
            if (session.value != 0L) {
                C.Logout(session.value)
                C.CloseSession(session.value)
            }
            C.Finalize()
        }
        return

    }

    private fun showUsbDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Jecarta")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

}
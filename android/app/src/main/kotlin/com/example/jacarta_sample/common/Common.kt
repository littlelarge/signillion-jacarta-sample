package ru.aladdin.mobileSDK

import org.pkcs11.jacknji11.*

object Common {
    @Throws(CKRException::class)
    fun findFirstObj(session: Long, objAttribs: Array<CKA>): Long {
        val found = LongArray(1)
        val count = LongRef(1)

        var rv = C.FindObjectsInit(session, objAttribs)
        if (rv != CKR.OK) {
            throw CKRException("C.FindObjectsInit", rv)
        }

        rv = C.FindObjects(session, found, count)
        if (rv != CKR.OK) {
            throw CKRException("C.FindObjects", rv)
        }

        rv = C.FindObjectsFinal(session)
        if (rv != CKR.OK) {
            throw CKRException("C.FindObjectsFinal", rv)
        }

        return found[0]
    }

    @Throws(CKRException::class)
    fun createSelfSignedCertificate(
        session: Long,
        publicKey: Long,
        privateKey: Long,
        certAttribs: Array<CKA>,
        dn: Array<String>,
        certificate: LongRef
    ): Long {
        var rv: Long
        val csr = CK_BYTE_PTR()

        val extensions = arrayOf("keyUsage", "digitalSignature,keyEncipherment")
        val certSerial = "11111111"
        val certValue = CK_BYTE_PTR()

        rv = C.EXTENSIONS.createCSR(session, publicKey, dn, csr, privateKey, null, extensions)
        if (rv != CKR.OK) {
            throw CKRException("createCSR", rv)
        }

        rv = C.EXTENSIONS.genCert(session, csr.value, privateKey, certSerial, null, 365, certValue)
        if (rv != CKR.OK) {
            throw CKRException("genCert", rv)
        }

        certAttribs[3] = CKA(CKA.VALUE, certValue.value)

        rv = C.CreateObject(session, certAttribs, certificate)
        if (rv != CKR.OK) {
            throw CKRException("C.CreateObject", rv)
        }

        return rv
    }
}

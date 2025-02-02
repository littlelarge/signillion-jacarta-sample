#ifndef __JCKT2_H__
#define __JCKT2_H__

#include <stddef.h>
#ifdef __GNUC__
  #include <stdlib.h>
  #include <string.h>
  #define __stdcall
  #define __cdecl
#endif


#ifdef _KT2__EXPORTS
	#define KT2_EXPORT __declspec(dllexport)
#endif

#ifdef _KT2__IMPORTS
	#define KT2_EXPORT __declspec(dllimport)
#endif

#ifndef KT2_EXPORT
	#define KT2_EXPORT
#endif

#if defined(_MSC_VER) || defined(__GNUC__) || defined(__MACH__)
	#pragma pack(push, 1)
#endif

typedef struct KT2_CONTEXT
{
	size_t cSize;  // must be initialized as sizeof(KT2Context)
	long(__cdecl *beginTransaction)(long hCard);
	long(__cdecl *endTransaction)(long hCard);
	long(__cdecl *transmit)(long hCard, const char *sendBuffer, int sendLength, char *recvBuffer, int *recvLength);
	long hCard;
}
KT2Context;

typedef struct KT2_BIGDATA
{
	size_t uiDataLength;//length of pvData in bytes
	void *pvData;
}
KT2BigData;

#if defined(_MSC_VER) || defined(__GNUC__) || defined(__MACH__)
	#pragma pack(pop)
#endif

typedef enum KT2_PROCESS_RES
{
	KT2_NO_ERROR,
	KT2_WRONG_CONTEXT,
	KT2_CMD_INVALID,
	KT2_TRANSACTION_ERROR,
	KT2_INSUFFICIENT_BUFFER,
	KT2_SIGNATURE_ERROR,
	KT2_INVALID_COMMAND_CLASS,
	KT2_TRANSMISSION_ERROR,
	KT2_SECURE_CHANNEL_ERROR,
	KT2_APPLET_NOT_FOUND,
	KT2_COMMAND_NOT_ALLOWED,
	KT2_NOT_AVAILABLE_IN_CURRENT_SESSION,
	KT2_INCORRECT_PIN,
	KT2_CTX_BUSY,
	KT2_TOO_MANY_DATA_TO_ENCRYPTION,
	
	KT2_INTERNAL_ERROR = -1,
}
KT2Response;

#if __GNUC__ >= 4
	#pragma GCC visibility push(default)
#endif

#ifdef __cplusplus
extern "C"
{
#endif

	KT2_EXPORT KT2Response __cdecl KT2Process(KT2Context *context, const void *command, int cLength, char *answer, int *aLength);
	
#ifdef __cplusplus
}
#endif

typedef KT2Response(__cdecl *PKT2Process)(KT2Context *context, const void *command, int cLength, char *answer, int *aLength);

#if __GNUC__ >= 4
	#pragma GCC visibility pop
#endif

#endif /* __JCKT2_H__ */

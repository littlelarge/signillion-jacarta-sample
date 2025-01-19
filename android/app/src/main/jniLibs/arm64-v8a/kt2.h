#ifndef __KT2_H__
#define __KT2_H__

// Класс команды поддерживаемый апплетом
#define SUPPORTED_CLA									0x80

// Код инструкции управления интерфейсной криптобиблиотекой
#define LIBRARY_INS										0xaa
// Команда начать сеанс
#define OPEN_SESSION_P1									0x10
// Команда завершить сеанс
#define CLOSE_SESSION_P1								0x20

// Код инструкции выполнения персонализации апплета
#define PERSO_INS										0xec
// Команда персонализировать апплет
#define PERSONALIZE_P1									0x10

// Код инструкций управления пользовательскими PIN/PUK-кодами
#define USER_INS										0x0a
// Код инструкций управления ключом администратора безопасности
#define SECURITY_OFFICER_INS							0x3a
// Команда проверить код пользователя или ключ администратора безопасности
#define VERIFY_P1										0x10
// Команда сменить код пользователя или ключ администратора безопасности
#define MODIFY_P1										0x20
// Команда установить PIN/PUK-коды
#define SET_P1											0x30

// Код инструкций управления объектами
#define OBJECT_MGMT_INS									0x1c
// Команда получить список объектов
#define GET_OBJECT_LIST_P1								0x10
// Команда импортировать доверенный открытый ключ
#define IMPORT_TRUSTED_PUBLIC_KEY_P1					0x20
// Команда создать доверенный секретный ключ
#define CREATE_TRUSTED_SECRET_KEY_P1					0x40
// Команда сгенерировать ключевую пару
#define GENERATE_KEY_PAIR_P1							0x50
// Команда удалить объект
#define DELETE_OBJECT_P1								0x60
// Команда сообщить открытый ключ
#define GET_PUBLIC_KEY_P1								0x70

// Код инструкций выполнения служебных команд
#define SERVICE_INS										0x38
// Команда включить гостевой режим
#define LOGOUT_P1										0x10
// Команда получить данные
#define GET_APPLET_DATA_P1								0x20
// Команда установить данные
#define SET_APPLET_DATA_P1								0x30
// Команда вычислить контрольную сумму
#define CALCULATE_APPLET_CHECKSUM_P1					0x40

// Код инструкций администрирования
#define ADMINISTRATION_INS								0x3c
// Команда сбросить счетчики неудачных попыток проверки PIN/PUK-кодов
#define RESET_FAILED_ATTEMPTS_COUNTER_P1				0x10
// Команда изменить парольную политику
#define CHANGE_PIN_POLICY_P1							0x20
// Команда инициализировать апплет
#define APPLET_INITIALIZATION_P1						0x30
// Команда сгенерировать ключевую пару апплета
#define GENERATE_APPLET_KEY_PAIR_P1						0x40

// Код инструкций выполнения криптографических команд
#define CRYPTO_INS										0x3e
// Команда подписать
#define SIGN_P1											0x10
// Команда проверить подпись
#define VERIFY_SIGNATURE_P1								0x20
// Команда начать хеширование
#define HASH_INIT_P1									0x30
// Команда продолжить хеширование
#define HASH_UPDATE_P1									0x40
// Команда завершить хеширование
#define HASH_FINAL_P1									0x50
// Команда начать вычисление HMAC
#define HMAC_INIT_P1									0x60
// Команда продолжить вычисление HMAC
#define HMAC_UPDATE_P1									0x70
// Команда завершить вычисление HMAC
#define HMAC_FINAL_P1									0x80
// Команда подготовить сессионный ключ
#define PREPARE_KEY_P1									0x90
// Команда начать преобразование по ГОСТ 28147-89
#define CRYPTO_INIT_P1									0xa0
// Команда продолжить шифрование
#define CRYPTO_UPDATE_P1								0xb0
// Команда завершить шифрование
#define CRYPTO_FINAL_P1									0xc0
// Команда сгенерировать последовательность случайных чисел
#define GENERATE_RANDOM_P1								0xd0

// Код инструкции управления файловой системой
#define FILE_SYSTEM_INS									0x4c
// Команда создать буфер для записи данных
#define CREATE_DATA_BUFFER_P1							0x10
// Команда записать данные
#define WRITE_DATA_P1									0x20
// Команда сохранить буфер как файл
#define SAVE_DATA_BUFFER_AS_FILE_P1						0x30
// Команда создать папку
#define CREATE_FOLDER_P1								0x40
// Команда удалить файл
#define DELETE_FILE_P1									0x50
// Команда прочитать файл
#define READ_FILE_P1									0x60
// Команда сообщить список файлов
#define GET_FILE_LIST_P1								0x70
// Команда выбрать файл
#define SELECT_FILE_P1									0x80
// Команда изменить настройки прав доступа
#define CHANGE_AC_P1									0x90
// Команда проверить подпись сертификата
#define VERIFY_CERTIFICATE_P1							0xa0

// Код инструкций управления защищенным каналом
#define SECURE_CHANNEL_INS								0x4e
// Команда начать установку защищенного канала
#define INITIALIZE_UPDATE_P1							0x10
// Команда завершить установку защищенного канала
#define EXTERNAL_AUTHENTICATE_P1						0x20
// Команда изменить статус защищенного канала
#define CHANGE_SECURE_CHANNEL_STATUS_P1					0x30

// Код инструкций встраивания в другие СКЗИ
#define EMBEDDED_INS									0x5c
// Команда сгенерировать ключ парной связи
#define GEN_SESSION_KEY_P1								0x10
// Команда проверить подпись внешним ключом
#define VERIFY_WITH_EXTERNAL_KEY_P1						0x20

// Тип кода / режим работы
typedef enum PIN_TYPE
{
	GUEST,										// гостевой режим работы
	SO,											// ключ администратора безопасности
	USER,										// PIN-код пользователя
	SIGN,										// PIN-код подписи
	PUK,										// PUK-код
    RESET,                                      // PIN-код сброса к заводским настройкам
	TRANSPORT									// транспортный ключ
}
PinType, WorkMode;

// Тип объекта
typedef enum OBJECT_TYPE
{
	PUBLIC_KEY = 1,								// открытые ключи
	SECRET_KEY,									// секретные ключи
	KEY_PAIR									// ключевые пары
}
ObjectType;

// Тип файла
typedef enum FILE_TYPE
{
	FILE_TYPE_EF = 1,							// файл данных
	FILE_TYPE_DF,								// папка
	FILE_TYPE_MF								// корневая директория
}
FileType;

enum ISO7816_OFFSET
{
	CLA, INS, P1, P2, LC, CDATA
};

typedef enum SW
{
	SW_WRONG_LENGTH = 0x6700,					// неверная длина команды

	SW_PIN_NOT_INITIALIZED = 0x6701,			// 6701h ПИН-код не инициализирован
	SW_PIN_LENGTH_INVALID,						// 6702h недопустимая длина ПИН-кода
	SW_PIN_BLOCKED,								// 6703h ПИН-код заблокирован
	SW_PIN_INVALID,								// 6704h неверный ПИН-код
	SW_PIN_NOT_VERIFIED,						// 6705h сеанс с текущим ПИН-кодом не был открыт
	SW_PIN_ALREADY_INITIALIZED,					// 6706h ПИН-код уже инициализирован
	SW_RNG_FAILED,								// 6707h ошибка ПДСЧ
	SW_OBJECT_NOT_FOUND,						// 6708h указанный объект не найден
	SW_OBJECT_STORAGE_FULL,						// 6709h превышено максимальное количество объектов указанного типа
	SW_SIGNATURE_INVALID,						// 670Ah неправильная подпись
	SW_INVALID_DATA,							// 670Bh указаны неверные данные в APDU команде
	SW_PIN_TYPE_INVALID,						// 670Ch неверный тип ПИН-кода
	SW_OBJECT_TYPE_INVALID,						// 670Dh неверный тип объекта
	SW_SIGN_PIN_NOT_PRESENT,					// 670Eh PIN-код подписи не предъявлен
	SW_OPERATION_NOT_PERMITTED,					// 670Fh операция не может быть выполнена
	SW_OPERATION_NOT_INITIALIZED,				// 6710h операция не была инициализирована
	SW_FILE_SYSTEM_FULL,						// 6711h превышено максимальное количество файлов
	SW_CAN_NOT_DELETE_MF,						// 6712h нельзя удалить корневую директорию файловой системы
	SW_DF_NOT_EMPTY,							// 6713h нельзя удалить не пустую директорию
	SW_FILE_NOT_FOUND,							// 6714h указанный файл не найден
	SW_INVALID_PARAMETERS,						// 6715h указан неверный параметр в ADPU команде
	SW_INVALID_OFFSET_OR_LENGTH,				// 6716h выход за границы значения файла при его записи/чтении
	SW_INVALID_FILE_TYPE,						// 6717h указан неверный тип файла
	SW_FILE_ALREADY_EXISTS,						// 6718h файл с указанным именем уже создан
	SW_AC_NOT_SATISFIED,						// 6719h нет прав для выполнения операции с указанным файлом
	SW_INVALID_LIFE_CYCLE_STATE,				// 671Ah неверное состояние персонализации/инициализации апплета
	SW_DECRYPTING_KEY,							// 671Bh ошибка расшифрования ключа
	SW_TOKEN_MEMORY_FULL,						// 671Ch на токене нет свободной памяти
	SW_INVALID_CERT_CONTENT,					// 671Dh неправильное содержание сертификата публичного ключа
	SW_PIN_NOT_FULFIL_PASSWORD_POLICY,			// 671Eh текущее значение ПИН-кода не удовлетворяет требованиям парольной политики
	SW_PIN_CHANGE_REQUIRED,						// 671Fh необходимо изменение текущего значения ПИН-кода
	SW_INTEGRITY_CONTROL_NOT_PASSED,			// 6720h ошибка динамического контроля целостности апплета
	SW_INVALID_SECURE_CHANNEL_COMMAND,			// 6721h неверное значение зашифрованной APDU команды
	SW_CMD_CAN_NOT_BE_TRANSMITTED_UNENCRYPTED,	// 6722h APDU команда не может быть передана в открытом виде
	SW_NONCONFORMANCE_KEY_ALG_OR_PARAMS,		// 6723h недопустимое значение алгоритма и/или параметра ключа
	SW_CMD_CAN_NOT_BE_PASSED_OVER_SC,			// 6724h APDU команда не может быть передана по защищенному каналу
	SW_PUBLIC_KEY_INVALID,						// 6725h неверное значение открытого ключа
	SW_ZERO_UKM,								// 6726h нулевое значение UKM
	SW_CPRO_INVALID_COUNTER_VALUE,				// 6727h недопустимое значение хотя бы одного из счетчиков защищенного канала КриптоПро
	SW_INVALID_PUBLIC_KEY_USAGE,				// 6728h недопустимое использование доверенного открытого ключа

	SW_UNKNOWN = 0x6f00,						// неизвестная ошибка
	SW_CLA_NOT_SUPPORTED = 0x6e00,				// неподдерживаемый класс команды
	SW_INS_NOT_SUPPORTED = 0x6d00,				// неподдерживаемая инструкция команды
	SW_APPLET_NOT_FOUND = 0x6A82,				// файл не найден (апплет отсутствует)
	SW_INCORRECT_P1P2 = 0x6a86,					// неверные параметры команды

	SW_NO_ERROR = 0x9000						// успешное выполнение команды
}
SW;

#define IS_SW(x) ((x) >= SW_WRONG_LENGTH && (x) <= SW_NO_ERROR)

#endif /* #ifndef __KT2_H__ */

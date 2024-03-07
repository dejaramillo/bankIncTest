# Manual De Usuario

Este tipo de API está diseñado para gestionar operaciones relacionadas con tarjetas bancarias y transacciones financieras. Permite a los usuarios generar números de tarjeta, activar y desactivar tarjetas, recargar saldos, realizar compras, obtener detalles de transacciones y anular transacciones. Está orientada a ofrecer una interfaz de programación para facilitar la integración de sistemas bancarios o financieros con aplicaciones que requieran manejar operaciones con tarjetas y transacciones de forma segura y eficiente.

## Guia De Ejecución Local
* **Clonar el repositorio**
* **Abrir el reposiotrio en el IDE de preferencia**
* **Ejecutar el archivo "BankincApplication"**
* **Ejecutar los endpoints listados a continuación**

### 1\. Generar Número de Tarjeta

* **Método:** GET
* **Endpoint:**`/api/v1/card/{customerId}/number`
* **Descripción:** Genera un nuevo número de tarjeta para un cliente dado.

### 2\. Activar Tarjeta

* **Método:** POST
* **Endpoint:**`/api/v1/card/enroll`
* **Cuerpo:** Objeto JSON que contiene `cardId`.
* **Descripción:** Activa una tarjeta con el ID proporcionado.

### 3\. Desactivar Tarjeta

* **Método:** DELETE
* **Endpoint:**`/api/v1/card/{cardId}`
* **Descripción:** Desactiva la tarjeta especificada por el ID de la tarjeta.

### 4\. Recargar Tarjeta

* **Método:** POST
* **Endpoint:**`/api/v1/card/balance`
* **Cuerpo:** Objeto JSON que contiene `cardId` y `balance`.
* **Descripción:** Añade una cantidad especificada de saldo a la tarjeta.

### 5\. Transacción de Compra

* **Método:** POST
* **Endpoint:**`/api/v1/transaction/purchase`
* **Cuerpo:** Objeto JSON que contiene `cardId` y `price`.
* **Descripción:** Procesa una transacción de compra por el monto especificado.

### 6\. Obtener Transacción

* **Método:** GET
* **Endpoint:**`/api/v1/transaction/{transactionId}`
* **Descripción:** Recupera los detalles de una transacción específica por su ID.

### 7\. Anular Transacción

* **Método:** POST
* **Endpoint:**`/api/v1/transaction/anulation`
* **Cuerpo:** Objeto JSON que contiene `cardId` y `transactionId`.
* **Descripción:** Anula una transacción específica para la tarjeta y el ID de transacción dados.

Este manual ofrece una visión general de cada punto de acceso API disponible en la colección "bankinc". Cada operación requiere el método HTTP correspondiente y, para algunas acciones, un payload JSON. Asegúrate siempre de reemplazar los marcadores de posición con valores reales al realizar solicitudes (por ejemplo, `{customerId}`, `{cardId}`, `{transactionId}`).
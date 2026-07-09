# Jorge Eduardo Gonzalez Cardoza 00016823

## Indicaciones

Recientemente, se utilizó AI para crear un sistema de gestion de una biblioteca, el cual ha generado varios errores, su trabajo es arreglarlo. Dado el siguiente caso de uso, explique y/o resuelva cada problema según se le pida.

---

## Consideraciones

La libreria crea automaticamente un correo con los nombres de la persona

---

## Problemas

### 1. Filtro por autor y género (10%)

QA ha reportado que el endpoint para obtener los libros puede filtrar por **autor** y por **género**, o por cualquiera de los dos de manera individual.

Actualmente:

- Filtrar únicamente por autor funciona correctamente.
- Filtrar únicamente por género funciona correctamente.
- Filtrar por **autor y género al mismo tiempo** provoca que el servidor falle.

**Instrucción:** Explique la causa del problema y resuélvalo.

No se transforma el string recibido en la request en un tipo GENRE antes de buscar si se utilizan ambas cosas, si estaba presente esto al hacerlo solo por genre. 

---

### 2. Error al volver a prestar un libro (10%)

Un usuario reportó que al pedir prestado el libro **The Selfish Gene**, devolverlo e intentar pedirlo prestado nuevamente, el servidor falla.

**Instrucción:** Explique la causa del problema y resuélvalo.

Los libros al llegar a 0 ejemplares disponibles, se guardan como no disponibles, pero al devolverlos no se vuelven a dejar como disponibles.

---

### 3. Cantidad de libros por género (10%)

Existe un endpoint que devuelve la cantidad de libros disponibles por género. Sin embargo, actualmente dicho endpoint falla.

**Instrucción:** Explique la causa del problema y resuélvalo.
El campo GENRE en la entity de book, no es un objeto, es un enum. Por lo tanto, no era posible hacer ENUM.getName(), solo se debía obtener su valor en string con String.valueOf(ENUM).
---

### 4. Error al consultar un libro por ID (10%)

Un miembro del equipo de frontend reporta que la siguiente llamada falla:

```http
GET /books?id=ed16ed1e-7017-4697-a08a-d28c09a74acf
```

**Instrucción:** Explique la causa del problema.

Este get se está realizando con el id como query param, pero el controller espera una path variable. Por lo que el endpoint correcto es: GET /books/ed16ed1e-7017-4697-a08a-d28c09a74acf

---

### 5. Error al crear un libro (10%)

QA ha reportado que el siguiente payload enviado al endpoint `POST /books` provoca un error:

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "genre": "classic",
  "isbn": "978-0132350884",
  "available": true,
  "availableCount": 5
}
```

**Instrucción:** Explique la causa del problema.
El género se está enviando como "classic", pero nuestro enum de género espera "CLASSIC", todo en mayúsculas, para poder pasar de string a ENUM. 
---

### 6. Devolución de libros no prestados (20%)

QA ha reportado que un usuario es capaz de devolver libros que nunca ha solicitado en préstamo.

**Instrucción:**

- Confirme si este comportamiento es realmente posible.
- Si es posible, explique la causa y resuelva el problema.
- Si no es posible, explique por qué, haciendo referencia al código correspondiente.

Nunca se valida se haya prestado antes el libro antes de registrar una devolucion. Ahora se cuenta y valida las veces que se presta sean mayores que las veces devuelto en una unidad.

---
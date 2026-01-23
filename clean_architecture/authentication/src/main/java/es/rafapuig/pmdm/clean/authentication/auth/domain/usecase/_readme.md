# Casos de uso

Cada acción del negocio es un UseCase.

Son clases cuyo nombre tiene como sufijo UseCase.
El método público se declara como operador invoke 
para llamar al caso de uso como una función.


👉 Ventaja: Se pueden testear sin Android, sin red, sin nada.

🧱 Regla de oro

UseCase = una intención clara del usuario

Login → entrar

Register → crear cuenta

Logout → salir

IsLoggedIn → preguntar estado

Nada más, nada menos.
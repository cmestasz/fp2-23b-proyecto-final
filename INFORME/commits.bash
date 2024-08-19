$ git add .gitignore

$ git commit -m ".gitignore actualizado para solo publicar la carpeta src"
[main e0cc937] .gitignore actualizado para solo publicar la carpeta src
 1 file changed, 3 insertions(+)

$ git add .

$ git commit -m "Ejemplo dado en la documentacion de JavaFX"
[main 118a77f] Ejemplo dado en la documentacion de JavaFX
 9 files changed, 155 insertions(+)
 create mode 100644 .vscode/settings.json
 create mode 100644 fase03/proyecto_final/VIDEOGAME/.vscode/launch.json
 create mode 100644 fase03/proyecto_final/VIDEOGAME/.vscode/settings.json
 create mode 100644 fase03/proyecto_final/VIDEOGAME/README.md
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/Controller.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/Main.fxml
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/Videogame.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/style.css
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/test.fxml
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/4.png

$ git push -f
Enumerating objects: 24, done.
Counting objects: 100% (24/24), done.
Delta compression using up to 4 threads
Compressing objects: 100% (19/19), done.
Writing objects: 100% (21/21), 3.69 MiB | 1.04 MiB/s, done.
Total 21 (delta 1), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (1/1), done.
To https://github.com/cmestasz/fp2-23b.git
 + af4068d...118a77f main -> main 

$ git add .

$ git commit -m "Menú principal"
[main e9a3e21] Menú principal
 9 files changed, 92 insertions(+), 34 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/Main Menu.fxml
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/barrack.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/barrack.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/waiting.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/waiting.psd

$ git add .

$ git commit -m "Controlador del menú principal"
[main 84ae869] Controlador del menú principal
 5 files changed, 131 insertions(+), 14 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainMenu/Main Menu.fxml
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainMenu/MainMenuController.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/Resolution.java

$ git add .

$ git commit -m "Modelo de servidor para manejar varias instancias locales del videojuego"
[main 5e5d1a0] Modelo de servidor para manejar varias instancias locales del videojuego
 4 files changed, 133 insertions(+), 56 deletions(-)
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/Main Menu.fxml
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainMenu/MainMenuServer.java

$ git add .

$ git commit -m "Servidor completo"
[main 1d626a9] Servidor completo
 1 file changed, 33 insertions(+), 11 deletions(-)

$ git add .

$ git commit -m "Funcionalidad de servidor para el menú principal completa"
[main abc9d8d] Funcionalidad de servidor para el menú principal completa
 5 files changed, 198 insertions(+), 58 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainMenu/Connection.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainMenu/Operation.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/Utils.java

$ git push
Enumerating objects: 76, done.
Counting objects: 100% (76/76), done.
Delta compression using up to 4 threads
Compressing objects: 100% (59/59), done.
Writing objects: 100% (65/65), 30.02 KiB | 2.73 MiB/s, done.
Total 65 (delta 32), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (32/32), completed with 7 local objects.
To https://github.com/cmestasz/fp2-23b.git
   118a77f..abc9d8d  main -> main

$ git add .

$ git commit -m "Inicio del juego vinculado entre ambas instancias"
[main a9a8d44] Inicio del juego vinculado entre ambas instancias
 9 files changed, 132 insertions(+), 64 deletions(-)
 rename fase03/proyecto_final/VIDEOGAME/src/FX/{ => MainGame}/Controller.java (94%)
 rename fase03/proyecto_final/VIDEOGAME/src/FX/{ => MainGame}/Main.fxml (98%)
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/test.fxml

$ git add .

$ git commit -m "Cambio de estructura y vinculo entre ambos controladores"
[main 0be0029] Cambio de estructura y vinculo entre ambos controladores
 8 files changed, 77 insertions(+), 35 deletions(-)
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/Controller.java
 rename fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/{Main.fxml => Main Game.fxml} (98%)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/MainGameController.java
 rename fase03/proyecto_final/VIDEOGAME/src/FX/{MainMenu/MainMenuServer.java => MainServer.java} (96%)
 rename fase03/proyecto_final/VIDEOGAME/src/{FX/MainMenu => Utils}/Connection.java (96%)
 rename fase03/proyecto_final/VIDEOGAME/src/{FX/MainMenu => Utils}/Operation.java (90%)

$ git add .

$ git commit -m "Comentarios descriptivos en las partes confusas"
[main 38e2aaf] Comentarios descriptivos en las partes confusas
 3 files changed, 21 insertions(+), 12 deletions(-)

$ git add .

$ git commit -m "Estructura del controlador del juego principal"
[main 8c4d1bd] Estructura del controlador del juego principal
 11 files changed, 86 insertions(+), 82 deletions(-)
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/Main Game.fxml
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/MainGame.fxml
 rename fase03/proyecto_final/VIDEOGAME/src/FX/MainMenu/{Main Menu.fxml => MainMenu.fxml} (100%)
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/4.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/barrack.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/barrack.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile.psd

$ git push
Enumerating objects: 72, done.
Counting objects: 100% (72/72), done.
Delta compression using up to 4 threads
Compressing objects: 100% (57/57), done.
Writing objects: 100% (61/61), 14.12 KiB | 2.02 MiB/s, done.
Total 61 (delta 29), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (29/29), completed with 5 local objects.
To https://github.com/cmestasz/fp2-23b.git
   abc9d8d..8c4d1bd  main -> main

$ git add .

$ git commit -m "Estructura visual del juego principal"
[main 49914f7] Estructura visual del juego principal
 6 files changed, 124 insertions(+), 35 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/Board.java

$ git add .

$ git commit -m "Modelo de base de datos y cambios en el menu principal"
[main 5b878e5] Modelo de base de datos y cambios en el menu principal
 6 files changed, 135 insertions(+), 37 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/DBConnector.java
 rename fase03/proyecto_final/VIDEOGAME/src/Utils/{Connection.java => ServerConnection.java} (91%)

$ git push
Enumerating objects: 48, done.
Counting objects: 100% (48/48), done.
Delta compression using up to 4 threads
Compressing objects: 100% (29/29), done.
Writing objects: 100% (31/31), 21.49 KiB | 4.30 MiB/s, done.
Total 31 (delta 13), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (13/13), completed with 9 local objects.
To https://github.com/cmestasz/fp2-23b.git
   8c4d1bd..5b878e5  main -> main

$ git add .

$ git commit -m "Clase que permite enviar y solicitar datos a la base de datos"
[main e1375c5] Clase que permite enviar y solicitar datos a la base de datos
 3 files changed, 74 insertions(+), 14 deletions(-)

$ git add .

$ git commit -m "Implementacion de la clase DBConnector"
[main acca9f6] Implementacion de la clase DBConnector
 5 files changed, 101 insertions(+), 70 deletions(-)

$ git add .

$ git commit -m "Clases para el juego principal"
[main dfbc1be] Clases para el juego principal
 17 files changed, 186 insertions(+), 23 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/BoardGUI.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/Classes/Archer.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/Classes/Knight.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/Classes/Soldier.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/Classes/Spearman.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/Classes/Swordsman.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_archer.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_archer.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_knight.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_knight.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_spearman.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_spearman.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_swordsman.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_swordsman.psd

$ git add .

$ git commit -m "Eleccion de reino y cambios en el menu principal"
[main 790dc08] Eleccion de reino y cambios en el menu principal
 5 files changed, 119 insertions(+), 32 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/settings.png

$ git add .

$ git commit -m "El tablero ahora se conecta entre ambos jugadores"
[main 5f9c49f] El tablero ahora se conecta entre ambos jugadores
 13 files changed, 227 insertions(+), 95 deletions(-)
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/FX/MainGame/BoardGUI.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/SerializableColor.java

$ git add .

 $ git commit -m "Envio de mensajes y conexion en el juego principal"
[main 45de600] Envio de mensajes y conexion en el juego principal
 10 files changed, 270 insertions(+), 75 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/MainGameOperation.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/MainMenuOperation.java
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/Operation.java

$ git add .

$ git commit -m "Nuevo sistema de mensajes"
[main 5207444] Nuevo sistema de mensajes
 3 files changed, 35 insertions(+), 16 deletions(-)

$ git add .

$ git commit -m "Chat de colores y mejor tratado"
[main 1afcb77] Chat de colores y mejor tratado
 3 files changed, 67 insertions(+), 26 deletions(-)

 $ git add .

$ git commit -m "Representacion visual de los soldados en ambos tableros"
[main cc5ebe5] Representacion visual de los soldados en ambos tableros
 13 files changed, 185 insertions(+), 78 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/BetterColor.java
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/SerializableColor.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/Tile.java
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Utils/VideogameConstants.java
 rename fase03/proyecto_final/VIDEOGAME/src/img/{tile.png => tile_tile.png} (100%)
 rename fase03/proyecto_final/VIDEOGAME/src/img/{tile.psd => tile_tile.psd} (100%)

$ git push
Enumerating objects: 173, done.
Counting objects: 100% (173/173), done.
Delta compression using up to 4 threads
Compressing objects: 100% (145/145), done.
Writing objects: 100% (154/154), 247.29 KiB | 7.98 MiB/s, done.
Total 154 (delta 87), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (87/87), completed with 10 local objects.
To https://github.com/cmestasz/fp2-23b.git
   5b878e5..cc5ebe5  main -> main

$ git add .

$ git commit -m "Implementacion de los tipos de soldado y sus posibles acciones"
[main 70d26d2] Implementacion de los tipos de soldado y sus posibles acciones
 23 files changed, 52 insertions(+), 43 deletions(-)
 rename fase03/proyecto_final/VIDEOGAME/src/{FX => }/MainServer.java (99%)
 rename fase03/proyecto_final/VIDEOGAME/src/{FX => }/Videogame.java (98%)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_charge.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_charge.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_dismount.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_dismount.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_mount.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_mount.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_move.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_move.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_schiltrom.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_schiltrom.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_shoot.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_shoot.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_sworddance.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_sworddance.psd

$ git add .

$ git commit -m "Implementacion de movimientos y ataques, se cancelaron bastantes planes que eran demasiado ambiciosos"
[main 4fc2a3f] Implementacion de movimientos y ataques, se cancelaron bastantes planes que eran demasiado ambiciosos
 26 files changed, 320 insertions(+), 107 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_attack.png
 rename fase03/proyecto_final/VIDEOGAME/src/img/{action_shoot.psd => action_attack.psd} (61%)
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_charge.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_charge.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_dismount.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_dismount.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_mount.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_mount.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_schiltrom.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_schiltrom.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_shoot.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_sworddance.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_sworddance.psd

$ git add .

$ git commit -m "Primera prueba de toda la funcionalidad completa"
[main 6520ee5] Primera prueba de toda la funcionalidad completa
 6 files changed, 85 insertions(+), 62 deletions(-)

$ git push
Enumerating objects: 98, done.
Counting objects: 100% (98/98), done.
Delta compression using up to 4 threads
Compressing objects: 100% (73/73), done.
Writing objects: 100% (76/76), 71.05 KiB | 5.46 MiB/s, done.
Total 76 (delta 46), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (46/46), completed with 13 local objects.
To https://github.com/cmestasz/fp2-23b.git
   cc5ebe5..6520ee5  main -> main

$ git add .

$ git commit -m "Correccion de los ultimos errores"
[main 924da03] Correccion de los ultimos errores
 12 files changed, 134 insertions(+), 72 deletions(-)

$ git push
Enumerating objects: 46, done.
Counting objects: 100% (46/46), done.
Delta compression using up to 4 threads
Compressing objects: 100% (23/23), done.
Writing objects: 100% (24/24), 25.04 KiB | 6.26 MiB/s, done.
Total 24 (delta 16), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (16/16), completed with 16 local objects.
To https://github.com/cmestasz/fp2-23b.git
   6520ee5..924da03  main -> main

$ git add .

$ git commit -m "Implementacion de más utilidades"
[main ea1dca9] Implementacion de más utilidades
 5 files changed, 27 insertions(+), 13 deletions(-)

$ git push
Enumerating objects: 28, done.
Counting objects: 100% (28/28), done.
Delta compression using up to 4 threads
Compressing objects: 100% (14/14), done.
Writing objects: 100% (15/15), 4.20 KiB | 1.40 MiB/s, done.
Total 15 (delta 11), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (11/11), completed with 10 local objects.
To https://github.com/cmestasz/fp

$ git add .

$ git commit -m "Ultimas correcciones de la logica y funciones"
[main f0cf82b] Ultimas correcciones de la logica y funciones
 2 files changed, 4 insertions(+), 1 deletion(-)

$ git push
Enumerating objects: 20, done.
Counting objects: 100% (20/20), done.
Delta compression using up to 4 threads
Compressing objects: 100% (10/10), done.
Writing objects: 100% (11/11), 3.40 KiB | 1.70 MiB/s, done.
Total 11 (delta 8), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (8/8), completed with 7 local objects.
To https://github.com/cmestasz/fp2-23b.git
   ea1dca9..f0cf82b  main -> main

$ git add .

$ git commit -m "Correcciones de la base de datos"
[main 12becf8] Correcciones de la base de datos
 3 files changed, 15 insertions(+), 7 deletions(-)

$ git push
Enumerating objects: 23, done.
Counting objects: 100% (23/23), done.
Delta compression using up to 4 threads
Compressing objects: 100% (11/11), done.
Writing objects: 100% (12/12), 1.12 KiB | 1.12 MiB/s, done.
Total 12 (delta 8), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (8/8), completed with 8 local objects.
To https://github.com/cmestasz/fp2-23b.git
   f0cf82b..12becf8  main -> main

$ git add .

$ git commit -m "Implementacion de mejoras visuales y forma final"
[main fd709bc] Implementacion de mejoras visuales y forma final
 47 files changed, 155 insertions(+), 99 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/White_Hammer.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/White_Moving.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_beach.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_beach.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_data.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_data.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_desert.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_desert.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_forest.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_forest.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_meadow.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_meadow.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_mountain.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_mountain.psd
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/beach.jpg
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/desert.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/icon_big_attack.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/icon_big_defence.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/icon_big_helmet.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/icon_big_target.png
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/meadow.jpg
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/mountain.jpg

$ git push
Enumerating objects: 94, done.
Counting objects: 100% (94/94), done.
Delta compression using up to 4 threads
Compressing objects: 100% (58/58), done.
Writing objects: 100% (59/59), 43.14 MiB | 2.47 MiB/s, done.
Total 59 (delta 16), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (16/16), completed with 13 local objects.
To https://github.com/cmestasz/fp2-23b.git
   12becf8..fd709bc  main -> main

$ git add .

$ git commit -m "Correcciones para el ejecutable y proyecto exportado"
[main 83baf9f] Correcciones para el ejecutable y proyecto exportado
 9 files changed, 67 insertions(+), 25 deletions(-)
 create mode 100644 fase03/proyecto_final/VIDEOGAME/SERVER.jar
 create mode 100644 fase03/proyecto_final/VIDEOGAME/VIDEOGAME.jar
 create mode 100644 fase03/proyecto_final/VIDEOGAME/data/dblogin.dat
 create mode 100644 fase03/proyecto_final/VIDEOGAME/src/Main.java

$ git add .

$ git commit -m "Ejecutables del videojuego en una carpeta separada"
[main 68308c2] Ejecutables del videojuego en una carpeta separada
 46 files changed, 569 insertions(+), 1 deletion(-)
 create mode 100644 fase03/proyecto_final/EJECUTABLES/SERVER.jar
 rename fase03/proyecto_final/{VIDEOGAME/SERVER.jar => EJECUTABLES/VIDEOGAME.jar} (77%)
 create mode 100644 fase03/proyecto_final/EJECUTABLES/data/dblogin.dat
 create mode 100644 fase03/proyecto_final/SERVER/.vscode/settings.json
 create mode 100644 fase03/proyecto_final/SERVER/README.md
 create mode 100644 fase03/proyecto_final/SERVER/SERVER.jar
 create mode 100644 fase03/proyecto_final/SERVER/src/FX/MainGame/Board.java
 create mode 100644 fase03/proyecto_final/SERVER/src/FX/MainGame/Classes/Archer.java
 create mode 100644 fase03/proyecto_final/SERVER/src/FX/MainGame/Classes/Knight.java
 create mode 100644 fase03/proyecto_final/SERVER/src/FX/MainGame/Classes/Soldier.java
 create mode 100644 fase03/proyecto_final/SERVER/src/FX/MainGame/Classes/Spearman.java
 create mode 100644 fase03/proyecto_final/SERVER/src/FX/MainGame/Classes/Swordsman.java
 rename fase03/proyecto_final/{VIDEOGAME => SERVER}/src/MainServer.java (99%)
 create mode 100644 fase03/proyecto_final/SERVER/src/Utils/BetterColor.java
 create mode 100644 fase03/proyecto_final/SERVER/src/Utils/DBConnector.java
 create mode 100644 fase03/proyecto_final/SERVER/src/Utils/MainGameOperation.java
 create mode 100644 fase03/proyecto_final/SERVER/src/Utils/MainMenuOperation.java
 rename fase03/proyecto_final/{VIDEOGAME => SERVER}/src/Utils/ServerConnection.java (100%)
 create mode 100644 fase03/proyecto_final/SERVER/src/Utils/Utils.java
 create mode 100644 fase03/proyecto_final/SERVER/src/Utils/VideogameConstants.java
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/White_Hammer.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/White_Moving.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_attack.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/action_move.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_beach.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_data.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_desert.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_forest.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_meadow.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/background_mountain.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/beach.jpg
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/desert.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/icon_big_attack.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/icon_big_defence.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/icon_big_helmet.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/icon_big_target.png
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/meadow.jpg
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/mountain.jpg
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_archer.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_knight.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_spearman.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_swordsman.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/tile_tile.psd
 delete mode 100644 fase03/proyecto_final/VIDEOGAME/src/img/waiting.psd

$ git push
Enumerating objects: 62, done.
Counting objects: 100% (62/62), done.
Delta compression using up to 4 threads
Compressing objects: 100% (41/41), done.
Writing objects: 100% (48/48), 137.06 MiB | 1.74 MiB/s, done.
Total 48 (delta 15), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (15/15), completed with 7 local objects.
remote: warning: See https://gh.io/lfs for more information.
remote: warning: File fase03/proyecto_final/EJECUTABLES/VIDEOGAME.jar is 50.32 MB this is larger than GitHub's recommended maximum file size of 50.00 MB
remote: warning: File fase03/proyecto_final/VIDEOGAME/VIDEOGAME.jar is 86.40 MB this is larger than GitHub's recommended maximum file size of 50.00 MB
remote: warning: GH001: Large files detected. You may want to try Git Large File Storage - https://git-lfs.github.com.
To https://github.com/cmestasz/fp2-23b.git
   fd709bc..68308c2  main -> main

$ git commit -m "Sistema de guardado y cargado de partidas"
[main 2186d87] Sistema de guardado y cargado de partidas
 13 files changed, 93 insertions(+), 16 deletions(-)
 create mode 100644 fase03/proyecto_final/EJECUTABLES/data/dbinit.dat
 create mode 100644 fase03/proyecto_final/EJECUTABLES/data/partida1.sav

$ git push
Enumerating objects: 47, done.
Counting objects: 100% (47/47), done.
Delta compression using up to 4 threads
Compressing objects: 100% (24/24), done.
Writing objects: 100% (26/26), 361.22 KiB | 374.00 KiB/s, done.
Total 26 (delta 14), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (14/14), completed with 13 local objects.
remote: warning: See https://gh.io/lfs for more information.
remote: warning: File fase03/proyecto_final/EJECUTABLES/VIDEOGAME.jar is 50.32 MB; this is larger than GitHub's recommended maximum file size of 50.00 MB
remote: warning: GH001: Large files detected. You may want to try Git Large File
 Storage - https://git-lfs.github.com.
To https://github.com/cmestasz/fp2-23b.git
   df9144e..2186d87  main -> main
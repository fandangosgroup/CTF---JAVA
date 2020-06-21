# CTF---JAVA
Capture the flag in java
#
 Impacto da LGPD no jogo
#
 esse jogo é baseado em Cifra de Cesar, uma forma rudimentar de
ofuscação de mensagens, não é considerado seguro hoje em dia, porém é
mais didático do que usar criptografia de mercado. Na hora de salvar a
frase/palavra no banco de dados, onde a palavra-chave é encriptada com a
Cifra de Cesar, o time A deve capturar a bandeira para conseguir a chave e
então desofuscar a frase/palavra inserida pelo primeiro jogador.
#
# Documentacao
#
DER
#
![](https://github.com/fandangosgroup/CTF---JAVA/blob/master/documentacao/der.png)
#
Diagrama de Classes
#
![](https://github.com/fandangosgroup/CTF---JAVA/blob/master/documentacao/uml_final.png)











- ### 1- Modo de jogo
 Para poder jogar capture the flag, precisará de dois jogadores e ambos terão
que iniciar o client do jogo para que tenha início a competição.

---

- ### 2- Objetivo
 O time A devera capturar a flag, que será gerada no mapa em
posições predefinidas.
 Para que o time B vença, ele deve evitar que o time A capture a flag
gerada pelo mapa.

---

- ### 3- Movimentação
Os controles para movimentação são as teclas W A S D.

---

- ### 4- Regras
 Vence o jogo quando o time A conseguir capturar a flag e leva-la para o lado
do seu campo, que estará no mapa adversário em posições pré-estabelecidas.
 Para que o time B mantenha o membro do time A longe da flag, o player
deve tocar no membro A para que o adversário retorne a um ponto pré-definido
pelo mapa.

 O jogo irá contar com uma área de 30X30, onde que o player não poderá
ultrapassar a área delimitada.
 Para que o time B vença, ele deve manter o membro da equipe A longe da
flag, interceptando o membro do time A.



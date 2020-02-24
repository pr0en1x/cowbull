Java игра бык-корова с web интерфейсом. 

 **Описание игры.** 

   Компьютер загадывает 4-х значное число. Цифры загаданного числа не 
   повторяются. Задача пользователя угадать загаданное число. У 
   пользователя неограниченное число попыток. В каждую попытку 
   пользователь дает компьютеру свой вариант числа. Компьютер сообщает 
   сколько цифр точно угадано (бык) и сколько цифр угадано без учета 
   позиции (корова). По ответу компьютера пользователь должен за 
   несколько ходов угадать число. 
   Пример: 
   7328 -- загаданное число 
   0819 -- 0Б1К 
   4073 -- 0Б2К 
   5820 -- 0Б1К 
   3429 -- 1Б1К 
   5960 -- 0Б0К 
   7238 -- 2Б2К 
   7328 -- 4Б0К (число угадано) 

**Функциональные требования**

   Пользователь должен регистрироваться для доступа к системе. 
   Пользователь должен видеть свои предыдущие попытки. 
   Компьютер ведет рейтинг пользователей (показатель -- среднее число 
   попыток до угадывания числа). 

**Использование технологий:**
Spring boot, Spring MVC, Spring Data JPA, Spring Security, Hibernate ORM, HyperSQL Database, Mustache.js.
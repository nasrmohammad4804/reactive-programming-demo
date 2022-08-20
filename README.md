# reactive-programming-demo
i want to create sample project with spring webflux and dockerize it
--------------------------------------------------------------------------
![download](https://user-images.githubusercontent.com/76038143/185766191-aa377d30-fe94-4a56-b8cc-1e5afeb41477.jpg)
----------------------------------------------------------------------------------------------------------------
# you can clone project and run with docker 
**1**. you need to build image of project with command of  **docker build -t user-mysql .**  (and able to alternative docker image instead of user-mysql)

----------------------------------------------------------------------------------------------------------------
**2**. you need to pull mysql image from docker hub with command of **docker pull mysql:latest** 
(note: if you already have it dont need to execute this command and get it from local )

----------------------------------------------------------------------------------------------------------------
**3**. after you need to run container of mysql with your own specification such as username and password and database name with command of
**docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=MohammadN@sr13804804 -e MYSQL_DATABASE=reactive-demo  -e MYSQL_PASSWORD=MohammadN@sr13804804 -d mysql**
 
----------------------------------------------------------------------------------------------------------------
**4**. and at the last stage you need to run this container and link project image with mysql for compatibility together and map on every port which you want on your local machine

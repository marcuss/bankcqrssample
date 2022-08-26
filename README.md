# BankAccount Microservice - CQRS Template


## Installation

1. Clone repo.
2. Run `docker network create --attachable -d bridge techbankNetwork`
2. Run docker-compose up 
3. Enter Mysql Adminer on http://localhost:8080/ user:root password: rootpassword
4. Enter Mongo Express on http://localhost:8081/
5. Enter Kafka UI on http://localhost:9090/


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[The Unlicense](https://unlicense.org/)

## Troubleshooting
If you get an error while connecting to MySQL stating:
```Access Denied for User 'root'@'localhost' (using password: YES)```

It could be for a number of reasons found on internet, but if you are reading this please try checking if the environment where you are running your docker compose does not already have a running mysql daemon, in that case is enough with stopping the other conflicting mysql, if not modifying the docker compose, or else, using the fillowing as connection url:
    jdbc:mysql://0.0.0.0:3306/bankAccount?createDatabaseIfNotExist=true
    
If you get an error while connecting to Mongo, please make sure the new user as described in: mongo-init.js is correctly created.

If you get an error running kafka-ui, it may be due to the image use, please reffer to the documentation and help on: https://github.com/provectus/kafka-ui


# Bank CQRS Exercise


## Installation

1. Clone repo.
2. Run docker-compose up 
3. Enter Mysql Adminer on http://localhost:8080/ user:root password: rootpassword
3. Enter Mongo Express on http://localhost:8081/


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[The Unlicense](https://unlicense.org/)

## Troubleshooting
If you get an error statying:
```Access Denied for User 'root'@'localhost' (using password: YES)```

It could be for a number of reasons found on internet, but if you are reading this please try checking if the environment where you are running your docker compose does not already have a running mysql daemon, in that case is enough with stopping the other conflicting mysql, if not modifying the docker compose, or else, using the fillowing as connection url:
    url: jdbc:mysql://0.0.0.0:3306/bankAccount?createDatabaseIfNotExist=true


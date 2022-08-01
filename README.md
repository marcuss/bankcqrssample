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
Sometimes mysql could have problems connecting, on internet there are plenty of solutions, but one very weird I found while working with the provided dockwer-compose file, was that the password was correct, and I could even connect using mysql workbench, but spring-boot failed to do so, only after using the following url connection string worked.
    url: jdbc:mysql://0.0.0.0:3306/bankAccount?createDatabaseIfNotExist=true


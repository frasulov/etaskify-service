## Taskify service

##### How to start app
* run `docker-compose up -d` command.
* Then run spring application using gradle in Intellij IDEA.
* App will be available in http://localhost:8080
* Swagger will be available in http://localhost:8090 (not implemented yet.)
* You can use [eTaskify.postman_collection.json](eTaskify.postman_collection.json)

### The task require more time to do with good quality. This is not my quality work.
### Missing Things
* RabbitMQ needed for firing messages.
  * root-user-created
    * consuming and creating organisation for this user.
  * user-assigned-and-email-requested
    * consuming and sending email
* Creating custom validations.
  * I tried to make errors dynamic as much as possible. However still some improvements needed.
* Some values are used without constants. 
* Some exception catches are missing.
* Tests are missing.
* And etc.
[![Build Status](https://travis-ci.org/maxvanny2010/boot.svg?branch=master)](https://travis-ci.org/maxvanny2010/boot)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/cdb02ab890a145778aef30620ad2a6eb)](https://www.codacy.com/manual/mailtime2010/for?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=maxvanny2010/for&amp;utm_campaign=Badge_Grade)

#### Project: Forum

**to used:** Spring Boot, Jpa Hibernate, Security (two roles admin / user), Postgresql(local and heroku), Liquibase,
FreeMarker, Turbolinks, Captcha, Javascript, Travis, Heroku.
> Pardon, the interface is simple:

###### https://bootforum.herokuapp.com/

(*please to wait a start heroku*)

##### Pages:

- check in.
- login.
- home page: list of posts.
- un/subscribe on user: you can delete / add / edit the data by post.
- registration by email.
- captcha
- personal account: user to delete / add / edit posts.

##### Startup process from IDE:

1. make a clone of the project.
2. to move between local base and heroku use application.properties file(local/heroku).
3. if running locally, to create a forum database in postgres.
4. go to application.properties and to change a profile to "local".
5. click Start Class Main.
6. go to http://localhost:8080.

ps.  *login: admin, password: 1. or registration by email.*




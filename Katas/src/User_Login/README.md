#[Library Kata „User login“](http://ccd-school.de/en/coding-dojo/library-katas/user-login/)

Develop a library to make user administration and login easier.

The contract of the library is this:
	
    interface IRegistration {
        void Register(string email, string password, string nickname);
        void Confirm(string registrationNumber);
    }
     
    interface ILogin {
        string Login(string loginname, string password);
        bool Is_login_valid(string token);
     
        void Request_password_reset(string email);
        void Reset_password(string resetRequestNumber);
    }
     
    interface IAdministration {
        User Current_user(string token);
     
        void Rename(string userId, string email, string nickname);
        void Change_password(string userId, string password);
        void Delete(string userId, string password);
    }
     
    class User {
        string Id;
        string Email;
        string Nickname;
        bool Confirmed;
        DateTime RegistrationDate;
        DateTime LastLoginDate;
        DateTime LastUpdatedDate;
    }

New users need to register first. At least they need to provide their email address. If no password is given, the library will generate one automatically, which later can be changed by the user.

Upon registration applicants will be sent a registration email with a link to follow in order to complete their registration. The link contains a registration number. Only if a registration has been confirmed will the applicant become a real user (User.Confirmed then is true).

Login is done with either the email address or the nickname plus the password after the user has been confirmed. If the login is successful a token is returned. It can be user later on whether client requests are valid. The structure of the token should be opaque to clients; clients should be unable to change/counterfeit tokens. To improve security tokens should have an expiration date.

If a user forgot her password she can order a password reset. An email will then be send to her email address with a link to actually reset the password. Only if the link is clicked a new password will be generated and send to the users email address.

User can administer their profile data. They access their data via the login token. Changes are passed to the service using the user’s id. Users are entities whose data can be changed – except for the id.

##Data

How to store user data is up to the implementer. However no passwords should be stored in their original form. Salted hashes should be used instead.

Unconfirmed registrations should be deleted automatically after an expiration data.

The library needs some configuration:

 - Connectionstring for the data store
 - SMTP connectionstring
 - URLs of pages links send via email should point to

How to store configuration data is up to the implementer.



by [Clean Code Developer School](http://ccd-school.de/)
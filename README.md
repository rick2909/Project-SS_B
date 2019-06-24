# Project-SS_B
This is a school project. developing an android app

## functions
* `login` needs `email`, `password`
* `getMaps` needs `id` from logged in user
* `getRooster` needs `id` from logged in user
* `upadateProtocolin` needs `id` from the protocol getting the update, `protocol` the text it self
* `getProtocol` needs `id` from the client, SO NOT LOGGED IN USER!
* `getOpmerkingen` needs `id` from the protocol (subject of change)
* `addOpmerking` needs `id` from the protocol, `Titel` titel van de opmerking, `opmerking` de opmerking zelf
* `getAllClienten` needs `id` logged in user

### returns
je krijgt altijd json terug json terug

#### login
```json
{
    "error": true or false,
    "message": "a message of what happend",
    "user": {
        "id":"1",
        "email":"test@test.nl",
        "wachtwoord":"wachtoord",
        "achternaam":"admin test",
        "initialen":"T.a.T",
        "Geslacht":"1",
        "locatie_id":"1",
        "functie_id":"1"
    }
}
```
#### `getMaps`
```json
{
    "error": true or false,
    "message": "a message of what happend",
    "user": {
        "id":"1",
        "email":"test@test.nl",
        "wachtwoord":"wachtoord",
        "achternaam":"admin test",
        "initialen":"T.a.T",
        "Geslacht":"1",
        "locatie_id":"1",
        "functie_id":"1"
    }
}
```
* `getRooster` 
* `upadateProtocolin` 
* `getProtocol` 
* `getOpmerkingen` 
* `addOpmerking` 
* `getAllClienten` 

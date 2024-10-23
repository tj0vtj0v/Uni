const express = require('express');
const app = express();
app.use(express.static('public'))

'use strict'
const send = require('./request')
send('+490000000000', '+490000000000', 'Hallo Papa, deswegen sollte man SMS nicht trauen,\nLG Tjorven');

// Wir laden contents aus dem public Ordner
app.use('/css', express.static(__dirname + '/public/css'))
app.use('/js', express.static(__dirname + '/public/js'))
app.use('/images', express.static(__dirname + '/public/images'))


/**
 * Starts the Express server.
 * @return {ExpressServer}
 */
function startServer() {
// Start the server
    return app.listen('8080', () => {
        console.log('Local Server Started on port 8080...');
    });
}

/**
 *  Diese Funktion wird aufgerufen, wenn Sie im Browser nach der Form fragen localhost:8080/form
 */
app.get('/form', function (req, res) {
    res.send("\
    <hmtl>\
        <header>\
            <link href=\"css\/style.css\" rel=\"stylesheet\" type=\"text\/css\" >\
            </header>\
        <body>\
            <h1>Input to send</h1>\
            <form action= \"/antwort\" method=\"get\">\
                <label for=\"team_name\">Number of the sender: </label>\
                <input id=\"team_name\" type=\"text\" name=\"name_field\" value=\"Telephone-number\">\
                <input type=\"submit\" value=\"OK\">\
            </form>\
        </body>\
    </hmtl>\
    ");

});

app.get('/antwort', function (req, res) {

    // Hier verarbeiten Sie die Eingaben aus den Feldern des Formulars req.query.email
    // Typischerweise nutzen Sie dazu eine Model Klasse, um Daten zu speichern oder zu laden


    //Hier generieren Sie eine Antwort die Gesendet wird (SpÃ¤ter View Klasse)
    res.send("\
        <html>\
            <header></header>\
            <body>\
                Successful delivered " + req.query.name_field + "\
            </body>\
        </html>\
        ");

});

app.get('/', function (req, res) {
    res.send("\
    <html>\
        <header></header>\
        <body>\
            <h1>Fake SMS</h1>\
            <br>\
            <a href=\"/form\">continue</a>\
        </body>\
    </html>\
    ");
});

app.get('/test', function (req, res) {
    data = '[{"name": "Tjorven", "age": "20"}, {"name": "Korbinian", "age": "20"}, {"name": "Tobias", "age": "20"}]'
    var mydata = JSON.parse(data);

    res.send(mydata)
})

startServer();


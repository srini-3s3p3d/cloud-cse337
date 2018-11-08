var nodemailer = require('nodemailer');
var formidable = require('formidable');
var http = require('http');
var fs = require('fs');
var port = process.env.PORT || 1337;
http.createServer(function (req, res) {
    let body = '';
    if (req.method === 'POST') {
        var form = new formidable.IncomingForm();
        form.parse(req, function (err, fields, files) {
            var text = fields.from;
            console.log(text);
            res.end(text);
            var transporter = nodemailer.createTransport({
                service: '"Gmail"', // no need to set host or port etc.
                auth: {
                    user: fields.from,
                    pass: 'yourpwd****'
                }
            });

            var mailOptions = {
                from: fields.from,
                to: fields.to,
                subject: fields.sub,
                html: fields.body,
                attachments :[
                    { path: 'somepath\\sample.txt' }
                ]
            };
            transporter.sendMail(mailOptions, function (error, info) {
                if (error) {
                    console.log(error);
                } else {
                    console.log('Email sent: ' + info.response);
                }
            });
        });
    }
}).listen(port);
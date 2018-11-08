var nodemailer = require('nodemailer');
var fs=require('fs');
var  transporter = nodemailer.createTransport({
    service: '"Gmail"', // no need to set host or port etc.
    auth: {
        user: 'yourmail@id.com',
        pass: 'yourpwd****'
    }
});

fs.readFile("./attachment.txt", function (err, data) {

var mailOptions = {
    from: 'yourmail@id.com',
    to: 'yourmail@id.com',
    subject: 'Sending Email using Node.js',
    html: 'Sending attachment now!',
    attachments: [{'filename': 'attachment.txt', 'content': data}]

};

transporter.sendMail(mailOptions, function (error, info) {
    if (error) {
        console.log(error);
    } else {
        console.log('Email sent: ' + info.response);
    }
});
});
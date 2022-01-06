var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
  res.status(200).json({
    "success": true
  });
});

router.get('/usersss', function (req, res) {
  console.log('who get in here / users');
  res.status(200).json({
    "message": "test"
  });
});

router.post('/post', function (req, res) {
  console.log('who get in here post / users');
  var inputData;
  req.on('data', (data) => {
    inputData = JSON.parse(data);
  });
  req.on('end', () => {
    console.log('user_id : ' + inputData.user_id + ' , name : ' + inputData.name);
  });
  res.write('OK!');
  res.end();
});

module.exports = router;

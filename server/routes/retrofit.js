var express = require("express");
var router = express.Router();

router.get("/get", function (req, res, next) {
  console.log("GET 호출 / data : " + req.query.data);
  console.log("path : " + req.path);
  res.send("get success");
});

router.post("/post", function (req, res, next) {
  console.log("POST 호출 / data : " + req.body.data);
  console.log("path : " + req.path);
  res.send("post success");
});

router.put("/put/:id", function (req, res, next) {
  console.log("UPDATE 호출 / id : " + req.params.id);
  console.log("body : " + req.body.data);
  console.log("path : " + req.path);
  res.send("put success");
});

router.delete("/delete/:id", function (req, res, next) {
  console.log("DELETE 호출 / id : " + req.params.id);
  console.log("path : " + req.path);
  res.send("delete success");
});

module.exports = router;

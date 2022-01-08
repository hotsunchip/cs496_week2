const axios = require("axios");
const cheerio = require("cheerio");

const getHTML = async (numlink, check) => {
  try {
    if (check == "1") {
      return await axios.get(
        "https://book.naver.com/search/search.naver?sm=sta_hty.book&sug=&where=nexearch&query=" +
          numlink
      );
    } else {
      return await axios.get(numlink);
    }
  } catch (err) {
    console.log(err);
  }
};

//바코드와 일치하는 책의 링크 받아오기
const mnd = async (codenum) => {
  let check = "1";
  let html = await getHTML(codenum, check);
  let $ = cheerio.load(html.data);
  const blink = $("#searchBiblioList")
    .find("li:nth-child(1) > dl > dt > a")
    .attr("href");
  check = "0";
  html = await getHTML(blink, check);
  $ = cheerio.load(html.data);
  $("span").remove();
  $(
    "#container > div.spot > div.book_info > div.book_info_inner > div:nth-child(2) > a.N\\=a\\:bil\\.publisher"
  ).remove();
  const explain = $("#bookIntroContent > p").text();
  $bookinfo = $("#container > div.spot > div.book_info");
  $buylink = $("#productListLayer > ul");
  // const bookauthor = $bookinfo
  //   .find("div.book_info_inner > div:nth-child(2)")
  //   .text();
  const book = {
    key: codenum,
    title: $bookinfo.find("h2 > a").text(),
    author: $bookinfo.find("div.book_info_inner > div:nth-child(2) > a").text(),
    price: $bookinfo
      .find("div.book_info_inner > div.price_area > div.lowest > strong")
      .text(),
    point: $("#txt_desc_point > strong:nth-child(2)").text(),
    blinkfirst: $buylink
      .find(
        "li:nth-child(1) > div > a.N\\=a\\:buy\\.cplist\\,r\\:1\\,i\\:yes24"
      )
      .attr("href"),
    blinksecond: $buylink
      .find(
        "li:nth-child(2) > div > a.N\\=a\\:buy\\.cplist\\,r\\:2\\,i\\:kyobo"
      )
      .attr("href"),
    blinkthrid: $buylink
      .find(
        "li:nth-child(3) > div > a.N\\=a\\:buy\\.cplist\\,r\\:3\\,i\\:aladdin"
      )
      .attr("href"),
    blinkfour: $buylink
      .find(
        "li:nth-child(4) > div > a.N\\=a\\:buy\\.cplist\\,r\\:4\\,i\\:ypbooks"
      )
      .attr("href"),
    explain: explain.substring(0, 240),
    bookimg: $(
      "#container > div.spot > div.book_info > div.thumb.type_end > div > a > img"
    ).attr("src"),
  };
  const bookCrawling = JSON.stringify(book);
  console.log(bookCrawling);
};

mnd("9788954445290"); //책 바코드

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
const getBook = async (codenum) => {
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

  $bookinfo = $("#container > div.spot > div.book_info");
  $paylink = $("#productListLayer > ul");

  const title = $bookinfo.find("h2 > a").text();
  $authorinfo = $(
    "#container > div.spot > div.book_info > div.book_info_inner > div:nth-child(2)"
  ).children("a");
  $authorinfo.each((idx, node) => {
    if (idx == 0) {
      author = $(node).text();
    }
  });
  const price = $bookinfo
    .find("div.book_info_inner > div.price_area > div.lowest > strong")
    .text();
  const imgbook = $(
    "#container > div.spot > div.book_info > div.thumb.type_end > div > a > img"
  ).attr("src");
  const review = $("#txt_desc_point > strong:nth-child(2)").text();
  const payone = $paylink
    .find("li:nth-child(1) > div > a.N\\=a\\:buy\\.cplist\\,r\\:1\\,i\\:yes24")
    .attr("href");
  const paytwo = $paylink
    .find("li:nth-child(2) > div > a.N\\=a\\:buy\\.cplist\\,r\\:2\\,i\\:kyobo")
    .attr("href");
  const paythree = $paylink
    .find(
      "li:nth-child(3) > div > a.N\\=a\\:buy\\.cplist\\,r\\:3\\,i\\:aladdin"
    )
    .attr("href");
  const payfour = $paylink
    .find(
      "li:nth-child(4) > div > a.N\\=a\\:buy\\.cplist\\,r\\:4\\,i\\:ypbooks"
    )
    .attr("href");
  const aboutbook = $("#bookIntroContent > p").text();

  const book = {
    codenum: codenum,
    title: title.substring(0, title.length - 1),
    author: author.substring(0, 20),
    price: price.substring(0, 10),
    review: review.substring(0, 10),
    love: "F",
    imgbook: imgbook.substring(0, 250),
    payone: payone.substring(0, 250),
    paytwo: paytwo.substring(0, 250),
    paythree: paythree.substring(0, 250),
    payfour: payfour.substring(0, 250),
    aboutbook: aboutbook.substring(0, 240) + "...",
  };

  // JSON 파일 형식으로 변환
  // const bookCrawling = JSON.stringify(book);

  console.log(book);
  return book;
};

module.exports = {
  getBook,
};

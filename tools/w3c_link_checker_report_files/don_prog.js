var sponsor_HP = new Array();
sponsor_HP['image'] = 'http://www.w3.org/QA/Tools/logos_sup/hp_logo.jpg';
sponsor_HP['text'] = 'The W3C validators are hosted on server technology donated by HP, and supported by community donations.';
sponsor_HP['height'] = 76;


var sponsor_Donate = new Array();
sponsor_Donate['image'] = 'http://www.w3.org/QA/Tools/I_heart_validator_lg';
sponsor_Donate['text'] = 'The W3C validators rely on community support for hosting and development.';
sponsor_Donate['height'] = 46;

var rand_no = Math.random();
rand_no = rand_no * 100;
rand_no = Math.ceil(rand_no);

var sponsor_chosen = new Array();
if (rand_no <= 33) {
    // show HP sponsor credit 1/3 of time, for now.
    sponsor_chosen = sponsor_HP;
}
else {
    sponsor_chosen = sponsor_Donate;
}

var program_div = document.getElementById("don_program");

var img_span = document.createElement("span");
img_span.setAttribute("id","don_program_img")
var imagelink = document.createElement("a"); imagelink.setAttribute("href","http://www.w3.org/QA/Tools/Donate")
var image = document.createElement("img"); 
image.setAttribute("src", sponsor_chosen["image"]);
 image.setAttribute("alt", "");
imagelink.appendChild(image); img_span.appendChild(imagelink); 
program_div.appendChild(img_span);

if (sponsor_chosen["height"] > 60 ) {
    program_div.style.minHeight = sponsor_chosen["height"]+"px";
    
}

var txt_span = document.createElement("span");
txt_span.setAttribute("id","don_program_text")
var text1 = document.createTextNode(sponsor_chosen["text"]); txt_span.appendChild(text1);
var br = document.createElement("br"); txt_span.appendChild(br);

var donate_link = document.createElement("a"); donate_link.setAttribute("href","http://www.w3.org/QA/Tools/Donate")
var text_donate_link = document.createTextNode("Donate"); 
donate_link.appendChild(text_donate_link); txt_span.appendChild(donate_link);
var text3 = document.createTextNode(" and help us build better tools for a better web."); 
txt_span.appendChild(text3);
program_div.appendChild(txt_span);
if (sponsor_chosen["height"] > 60 ) {
    txt_span.style.paddingTop = (sponsor_chosen["height"]-40)+"px";
    txt_span.style.paddingBottom = (sponsor_chosen["height"]-40)+"px";
    
}

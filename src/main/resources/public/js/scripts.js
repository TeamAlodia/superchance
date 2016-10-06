
function loadPage(){
  alert("Welcome to Super Chance");
}

window.onload=function(){
    window.setTimeout("redirect()", 1000);
};

function redirect() {
  document.battle.submit();
}

window.addEventListener("keypress", turnLogic, false);

function turnLogic(event){
  if (event.keyCode === 97){
    $( "#p1-input" ).attr("value", "0");

  } else if (event.keyCode === 115){
    $( "#p1-input" ).attr("value", "1");

  } else if (event.keyCode === 100){
    $( "#p1-input" ).attr("value", "2");

  } else if (event.keyCode === 102){
    $( "#p1-input" ).attr("value", "3");

  } else if (event.keyCode === 103){
    $( "#p1-input" ).attr("value", "4");

  } else if (event.keyCode === 104){
    $( "#p2-input" ).attr("value", "0");

  } else if (event.keyCode === 106){
    $( "#p2-input" ).attr("value", "1");

  } else if (event.keyCode === 107){
    $( "#p2-input" ).attr("value", "2");

  } else if (event.keyCode === 108){
    $( "#p2-input" ).attr("value", "3");

  } else if (event.keyCode === 59){
    $( "#p2-input" ).attr("value", "4");

  }
};

$(document).ready(function(){



});

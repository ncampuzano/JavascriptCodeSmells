var s = "Hola" + "Hello" + "Mundo" + "World"
$(document).ready(function() {
  $('.Component')
    .find('button')
      .addClass('Component-button--action')
      .click(function() { alert('HEY!' + ' Alert'); })
    .end()
    .mouseenter(function() { $(this).addClass('Component--over'); })
    .mouseleave(function() { $(this).removeClass('Component--over'); })
    .addClass('initialized');
});
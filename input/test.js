function Person() {
  this.teeth = [{ clean: false }, { clean: false }, { clean: false }];
};

Person.prototype.brush = function() {
  var that = this;

  this.teeth.forEach(function(tooth) {
    that.clean(tooth);
  });

  console.log('brushed');
};

Person.prototype.clean = function(tooth) {
  tooth.clean = true;
}

var person = new Person();
person.brush();
console.log(person.teeth);

var build = function(id, href, text) {
  return $( "<div id='tab'><a href='" + href + "' id='" + id + "'>" +
    text + "</a></div>" );
}
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
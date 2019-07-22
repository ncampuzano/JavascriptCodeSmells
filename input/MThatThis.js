function Person() {
  this.teeth = [{ clean: false }, { clean: false }, { clean: false }];
};

Person.prototype.brush = function() {
  this.teeth.forEach(function(tooth) {
    this.clean(tooth);
  }.bind(this));
  console.log('brushed');
};

Person.prototype.clean = function(tooth) {
  tooth.clean = true;
}

var person = new Person();
person.brush();
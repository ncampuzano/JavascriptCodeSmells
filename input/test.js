/*
function isSomeNumString(numString){
    return numString === 5;
}

function Person() {
  this.teeth = [{ clean: false }, { clean: false }, { clean: false }];
};

Person.prototype.brush = function() {
  var that = this;

  this.teeth.forEach(function clean(tooth) {
    that.clean(tooth);
  });

  console.log('brushed');
};

Person.prototype.clean = function(tooth) {
  tooth.clean = true;
}
var s = () => {
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

var search = document.querySelector('.Autocomplete');
search.addEventListener('input', function(e) {
    // Make Ajax call for autocomplete
    console.log(e.target.value);
});


  // Example 1 - Using Angular JS $http promises

  function getUsers(callback){
    $http.get('/users')
      .then(function(res){
        callback(res);
      });
  }

  getUsers(function(res){
    $scope.users = res.users;
  });


  // Example 2 - Using Native NodeJS promises

  function getEmployees(){
    return new Promise(function(resolve, reject){
      resolve(doSomething());
    });
  }

  function getData(callback){
    getEmployees()
      .then(function(res){
        callback(res);
      });
  }

  getData(function(res){
    console.log(res.employees);
  });
  */

function stringAdd(numString){
    var val = parseInt(numString);
    if(numString === NaN){
      return 0;
    }
    else{
      return val;
    }
}


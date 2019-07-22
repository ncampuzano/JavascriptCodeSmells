function getUsers(callback){
    $http.get('/users')
      .then(function(res){
        callback(res);
      });
}

getUsers(function(res){
    $scope.users = res.users;
});


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


function stringAdd(numString){
    var val = parseInt(numString);
    if(numString === NaN){
      return 0;
    }
    else{
      return val;
    }
}


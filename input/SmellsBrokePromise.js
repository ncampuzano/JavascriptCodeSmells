getUsers(function(res){
  $scope.users = res.users;  
});

getData(function(res){
  console.log(res.employees);
});
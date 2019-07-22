getUsers().then(function(res){
  $scope.users = res.users;  
});

getData().then(function(res){
  console.log(res.employees);
});    
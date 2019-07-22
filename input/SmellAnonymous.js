var search = document.querySelector('.Autocomplete');

search.addEventListener('input', function(e) {
  console.log(e.target.value);
});
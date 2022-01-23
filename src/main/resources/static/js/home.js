 $(document).ready(function () {
 	 $('#submit-button').click(e =>{
		const name = $('#nameInput').value
		const code = $('#codeInput').value
		const range = $('#rangeInput').value
		const number = $('#numberInput').value
		const obj = {
					name,
					code,
					range,
					number
					}
		fetchPostInfo("http://localhost:8080/register",obj)
		.then(result => console.log(result))					
})
	
function fetchPostInfo(url,formData){
	fetch(url, {
	   method: "POST",
	   body: formData
 })
}
    })
 	
 
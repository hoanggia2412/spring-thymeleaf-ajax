 $(document).ready(function () {
 	$(".details-button").click(e => {	
	 	const code = $(e.target).attr('code')
		const element =document.getElementById('info_'+code)
		if (element.getElementsByTagName('*').length > 0) {
			return
		}  
     	fetchInfo(code).then(result => {  
		     element.innerHTML = result
		     console.log(result)
		     })
     
    })
    async function fetchInfo(id){    
	    let res = await fetch(`http://localhost:8080/${id}`)
	    res = await res.text()
	    //var parser = new DOMParser();
	    //var html =  await parser.parseFromString(res, 'text/html');
	    return res
    }
    })
 	
 
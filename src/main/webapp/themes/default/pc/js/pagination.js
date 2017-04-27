function fnPagination(type,param){
	var oPageNumber = document.getElementById("pageNumber");
	var pageNumber = parseInt(oPageNumber.value);
	switch (type)
	{
	case 1://Next
		oPageNumber.value = (pageNumber+1);
		break;
	case 2://Last
		oPageNumber.value = param;
		break;
	case 3://Previous
		oPageNumber.value = (pageNumber-1);
		break;
	case 4://First
		oPageNumber.value = 1;
		break;
	case 5://Page size change
		oPageNumber.value = 1;
		break;
	case 6://sort
		var oSortColumn = document.getElementById("sortColumn");
		var oSortOrder = document.getElementById("sortOrder");
		if(oSortColumn.value == param && oSortOrder.value != "DESC") 
			oSortOrder.value = "DESC";
		else oSortOrder.value = "ASC";
		oSortColumn.value = param;
		break;
	}
	document.paginationForm.submit();
}
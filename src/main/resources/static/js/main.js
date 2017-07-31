function obtenerFragmentPaginado(url, page, size) {
	url = url + '&page=' + page + '&size=' + size;
	$('#divResultado').load(url);
}
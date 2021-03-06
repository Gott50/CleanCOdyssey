<?php
function detectAnagrams( $word, $list ) {
	return array_values( array_filter( $list, function ( $e ) use ( $word ) {
		$e    = mb_strtolower( $e );
		$word = mb_strtolower( $word );
		if ( strlen( $e ) != strlen( $word ) || $e == $word ) {
			return false;
		}

		$difference = array_reduce( str_split( $word ), function ( $carry, $item ) {
			unset( $carry[ array_search( $item, $carry ) ] );
			return $carry;
		}, str_split( $e ) );

		return sizeof( $difference ) == 0;
	} ) );
}
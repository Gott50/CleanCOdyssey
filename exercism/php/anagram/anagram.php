<?php
function detectAnagrams( $word, $list ) {
	return array_values(array_filter($list,function ($e) use ( $word ) {
		$e = strtolower( $e );
		$word = strtolower( $word );
		if (strlen($e) != strlen($word)||$e == $word) return false;

		$difference = array_reduce(str_split( $word ),function ($carry , $item){
			$index = array_search($item, $carry);
			unset( $carry[$index] );
			return $carry;
		},str_split($e));

		return sizeof( $difference ) == 0;
	}));
}
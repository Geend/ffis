<?php
class modOpIndicatorHelper
{
    public static function getOperation($params)
    {		
		$flyingJson = file_get_contents('http://state.haec.de/flying/get');
		$flying = json_decode($flyingJson);

		$state = $flying->state[0];
		
		if ($state === "net.torbenvoltmer.ffis.common.state.TrueState")
			return "op";
		elseif($state === "net.torbenvoltmer.ffis.common.state.FalseState")
			return "nop";
		else
			return "ni";    

	}
}
?>

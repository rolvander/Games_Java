/// @description Insert description here
// You can write your code in this editor
#region EXPLOSION

if(obj_player.right){
	image_xscale = 1
	instance_create_depth(x + 6,y,-y,obj_explosionH)
}else if(obj_player.left){
	image_xscale = -1
	instance_create_depth(x - 19,y,-y,obj_explosionH)
}else if(obj_player.down){
	image_yscale = 1
	instance_create_depth(x,y + 7,-y,obj_explosionV)
}else if(obj_player.up){
	instance_create_depth(x,y + 7,-y,obj_explosionV)
}


#endregion

instance_destroy();

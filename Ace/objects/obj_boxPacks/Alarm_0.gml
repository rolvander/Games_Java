/// @description Insert description here
// You can write your code in this editor
instance_destroy()

if(irandom(100) > 30){
	var packss =choose(obj_energy, obj_lifePack)
	instance_create_depth(x - 15,y + 15,-1,packss)
}
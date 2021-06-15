/// @description Insert description here
// You can write your code in this editor
instance_destroy()
var packs = choose(obj_lifePack, obj_energy)
instance_create_depth(x,y-10,-1,packs)

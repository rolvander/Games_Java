/// @description Insert description here
// You can write your code in this editor
depth = -1;
	
	scr_controles();

	scr_movimento();

	scr_tiro();

	scr_vida();

	scr_machineState();


#region CAMERA

	camX+= (x - (view_wport[0]/2) - camX)*0.05
	camY+=(y - (view_hport[0]/2) - camY)*0.05
	

	var camx = clamp(camX, 0, room_width-view_wport[0]+240)
	var camy = clamp(camY, 0, room_height-view_hport[0]+180)


	camera_set_view_pos(view_camera[0], camx, camy);

#endregion

#region DIALOGO
if(distance_to_object(obj_box) < 60){
	caixa = true
	keyy = false
	gate = false
	
	
}else if(distance_to_object(obj_gate) < 8){
	caixa = false
	keyy = false
	gate = true
	
}else if(distance_to_object(obj_key) < 13){
	caixa = false
	keyy = true
	gate = false
}else {
	caixa = false
	keyy = false
	gate = false
	
}

#endregion





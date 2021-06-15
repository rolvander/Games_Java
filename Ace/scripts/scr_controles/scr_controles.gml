	key_right = keyboard_check(ord("D"))
	key_left = keyboard_check(ord("A"))
	key_up = keyboard_check(ord("W"))
	key_down = keyboard_check(ord("S"))
	key_fast = keyboard_check(vk_space);
	key_pause = keyboard_check(vk_escape);
	
	
	//VIDA
	if lifePlayer <= 0 {
		lifePlayer = 0
	}
	//não passa de 100
	if lifePlayer > 100{
		lifePlayer = 100
	}
	
	//ENERGIA
	if energy <= 0 {
		energy = 0
	}
	//não passa de 100
	if energy > 100{
		energy = 100
	}
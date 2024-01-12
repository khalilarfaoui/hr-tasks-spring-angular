



import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SprintService } from 'src/app/services/sprint.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-sprint',
  templateUrl: './add-sprint.component.html',
  styleUrls: ['./add-sprint.component.scss']
})
export class AddSprintComponent {
 me : any
  sprintForm = this.fb.group({
    user: [{}, Validators.required],
    nomSprint: [null, Validators.required],
    datedebut: [null, Validators.required],
    status: ["", Validators.required],
    datefin: [null, Validators.required],
  
  });

  id = localStorage.getItem('id')

 

  constructor(private fb: FormBuilder , private sprintService : SprintService , private userService : UserService) {
    this.userService.getUserById(this.id).subscribe(res=>{
      console.log(res)
      this.me = res
    })
  }

  onSubmit(): void {
    this.sprintForm.value.user = this.me
    this.sprintForm.value.status = 'IN_PROGRESS'
    console.log(this.sprintForm.value)
    this.sprintService.addSprint(this.sprintForm.value).subscribe(res=>{
      console.log(res)
      this.sprintForm.reset({})
    })
  }
}


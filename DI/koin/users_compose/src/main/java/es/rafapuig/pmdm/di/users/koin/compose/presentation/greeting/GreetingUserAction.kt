package es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting

sealed interface GreetingUserAction {
    class OnNameChange(val inputName: String) : GreetingUserAction
    object OnGreet : GreetingUserAction
}
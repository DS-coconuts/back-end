def login(request):
    if request.method == 'POST':
        uEmail = request.POST['email']
        uPassword = request.POST['password']
        if uEmail == '':
            messages.warning(request, 'Kayıtlı Mail adresini giriniz.')
        elif uPassword == '':
            messages.warning(request, 'Kayıtlı şifrenizi giriniz.')
        else:
            user = authenticate(request, username=uEmail, password=uPassword)
        return redirect('login')
    else:
        return render(request, 'todolist/login.html')
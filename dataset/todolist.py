class toDoLists(models.Model):
    user = models.ForeignKey(
        User, related_name="user", on_delete=models.CASCADE)
    list_name = models.TextField(null=False, blank=False)
    created_at = models.DateTimeField(auto_now_add=True)

    class Meta:
        ordering = ('-created_at',)

    def __str__(self):
        return self.list_name
def get_entry_from_params(params):
    entry_id = params.get('entry', None)
    if entry_id is None:
        raise Exception("Invalid Entry")

    try:
        entry = EntryEnvelope.objects.filter(entry_id=entry_id)
    except EntryEnvelope.DoesNotExist:
        raise Exception("Invalid Entry")

    if entry.count() < 1:
        raise Exception("Invalid Entry")

    return entry[0]

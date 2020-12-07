# Generated by Django 3.1.1 on 2020-09-21 09:02

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('demacia', '0002_champion'),
    ]

    operations = [
        migrations.CreateModel(
            name='VideoPost',
            fields=[
                ('videopostno', models.AutoField(primary_key=True, serialize=False)),
                ('title', models.CharField(max_length=200)),
                ('video', models.CharField(max_length=250)),
                ('data', models.CharField(max_length=250)),
                ('isprivate', models.BooleanField()),
                ('userno', models.ForeignKey(db_column='userno', on_delete=django.db.models.deletion.DO_NOTHING, to='demacia.user')),
            ],
            options={
                'db_table': 'videopost',
            },
        ),
        migrations.CreateModel(
            name='VideoPostLikes',
            fields=[
                ('vpostlikeno', models.AutoField(primary_key=True, serialize=False)),
                ('userno', models.ForeignKey(db_column='userno', on_delete=django.db.models.deletion.DO_NOTHING, to='demacia.user')),
                ('videopostno', models.ForeignKey(db_column='videopostno', on_delete=django.db.models.deletion.DO_NOTHING, to='demacia.videopost')),
            ],
            options={
                'db_table': 'videopostlikes',
            },
        ),
        migrations.CreateModel(
            name='ReplyLikes',
            fields=[
                ('replikeno', models.AutoField(primary_key=True, serialize=False)),
                ('replyno', models.ForeignKey(db_column='replyno', on_delete=django.db.models.deletion.DO_NOTHING, to='demacia.reply')),
                ('userno', models.ForeignKey(db_column='userno', on_delete=django.db.models.deletion.DO_NOTHING, to='demacia.user')),
            ],
            options={
                'db_table': 'replylikes',
            },
        ),
        migrations.CreateModel(
            name='Match',
            fields=[
                ('matchno', models.AutoField(primary_key=True, serialize=False)),
                ('recommand_champion', models.CharField(max_length=200)),
                ('lane', models.CharField(max_length=100)),
                ('userno', models.ForeignKey(db_column='userno', on_delete=django.db.models.deletion.DO_NOTHING, to='demacia.user')),
            ],
            options={
                'db_table': 'match',
            },
        ),
    ]
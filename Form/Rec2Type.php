<?php

namespace App\Form;

use App\Entity\Employee;
use App\Entity\Rec2;
use FOS\CKEditorBundle\Form\Type\CKEditorType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class Rec2Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('texte',CKEditorType::class)
            ->add('date')
            ->add('employee',EntityType::class,['class'=>Employee::class,'choice_label'=>'id']);
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Rec2::class,
        ]);
    }
}

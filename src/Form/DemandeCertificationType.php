<?php

namespace App\Form;

use App\Entity\DemandeCertification;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;




use App\Entity\Certification;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


class DemandeCertificationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
        ->add('Certification', EntityType::class, [
            'class'=>Certification::class,
            'choice_label'=>'nom',
            
            ])   
            ->add('NomParticipant')
            ->add('PrenomParticipant')
            ->add('DateDemande')
            ->add('ExperienceParticipant')
            ->add('Email')
            
                ;
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => DemandeCertification::class,
        ]);
    }
}

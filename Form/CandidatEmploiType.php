<?php

namespace App\Form;

use App\Entity\CandidatEmploi;
use App\Entity\Entreprise;
use App\Entity\Employe;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CandidatEmploiType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('DateCandiature')
            ->add('email')
            ->add('IdEmploye',EntityType::class, [
                    'class'=>Employe::class,
                     'choice_label'=>'nom'

                     ])
                     ->add('IdEntreprise', EntityType::class, [
                        'class'=>Entreprise::class,
                        'choice_label'=>'nom',
                        
                        ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => CandidatEmploi::class,
        ]);
    }
}
